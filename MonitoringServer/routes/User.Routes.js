var express = require('express');
var routes = express.Router();
var User = require('../controllers/User.Controller');
//
// routes.post('/editProfile', User.editProfile);
// routes.post('/login',User.login);
// routes.post('/signup',User.signup);
// routes.post('/forgotPassword', User.forgotPassword);
// routes.get('/changePassword', User.changePassword);
//
// //Helper Functions
// routes.post('/checkAuthorisation', User.checkAuthorisation);
// routes.post('/upload', User.upload);
// routes.get('/getUserFromJWT',User.getUserFromJWT);
// routes.post('/getUserById',User.getUserFromId);
// routes.post('/getUser',User.getUser);
//
// //Update User Chat Id's
// routes.post('/updateChats', User.updateChats);
// routes.get('/getChats', User.getChats);
// routes.delete('/deleteChats', User.deleteChats);
//
//
// //Add Card Details
// routes.post('/addCardDetails', User.addCardDetails);
// routes.delete('/deleteCardDetails', User.deleteCardDetails);
//
// routes.get('/rateUser', User.rateUser);


routes.get('/test', User.test);

routes.get('/getAllLinks', User.getAllLinks)

routes.get('/getLinkFromId', User.getLinkFromId)

routes.post('/signup', User.signup);

routes.post('/approve', User.approve);

routes.post('/getAllUserLinks', User.getAllUserLinks);

module.exports = routes;
