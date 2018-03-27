var express = require('express');
var routes = express.Router();
var Admin = require('../controllers/Admin.Controller');


routes.get('/getAllLinks', Admin.getAllLinks)

routes.get('/getLinkFromId', Admin.getLinkFromId);

routes.post('/approve', Admin.approve);


module.exports = routes;
