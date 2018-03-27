var express = require('express');
var routes = express.Router();
var User = require('../controllers/User.Controller');



routes.post('/signup', User.signup);

routes.post('/getAllUserLinks', User.getAllUserLinks);

module.exports = routes;
