'use strict';

var Link = require('../models/Link');
var request = require('request');
var busboy = require('connect-busboy');
var http = require('http');
var xmlHTTP = require('xhr2');
var fs = require('fs');
var path = require('path');
//var nodemailer = require('nodemailer');

var async = require('async');
var crypto = require('crypto');

var bcrypt = require('bcrypt');
var User = require("../models/User");
var jwt = require('jsonwebtoken');
var config = require('../config');



/**
 * @api {post} /user/:signup Signup as a new user
 * @apiGroup User
 *
 * @apiParam {String} email address of the user - Must be unique
 * @apiParam {String} password password to be used
 *
 * @apiSuccess {String} JWT JWT Token - expires in 24 hours
 */
exports.signup = function(req, res) {
    if(req.body && req.body.email && req.body.password ) {

        var hashedPassword = bcrypt.hashSync(req.body.password, 10);

        User.create({
                email : req.body.email,
                password : hashedPassword
            },
            function (err, user) {
                if (err) return res.status(500).send("There was a problem registering the user`.");

                // if user is registered without errors
                // create a token
                var token = jwt.sign({ id: user._id }, config.secret, {
                    expiresIn: 86400 // expires in 24 hours
                });

                res.status(200).send({ success: true, token: token });
            });


    } else {
        res.status(403).send({success: false, msg: "Please send all information"});
    }

}



/**
 * @api {post} /user/:getAllUserLinks Get All Links
 * @apiGroup User
 *
 * @apiParam {String} email address of the user - Must be unique
 * @apiParam {String} password password to be used
 * @apiParam {String} token JWT Token for authentication
 *
 * @apiSuccess {String} Links Links in JSON Format
 */
exports.getAllUserLinks = function (req, res) {
    if(req.body.email && req.body.password && req.body.token) {

        var token = req.body.token; //req.headers['x-access-token'];
        if (!token)
            return res.status(403).send({ auth: false, message: 'No token provided.' });

        // verifies secret and checks expiry
        jwt.verify(token, config.secret, function(err, decoded) {
            if (err)
                return res.status(500).send({ auth: false, message: 'Failed to authenticate token.' });

            // if everything is good, save to request for use in other routes
            req.userId = decoded.id;

            Link.find().then(function(result) {
                return res.status(200).send({success: true, Links: result});
            });

        });


    } else {

        return res.status(401).send("Please send all data")
    }


}