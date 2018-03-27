var express = require('express');
var routes = express.Router();


var User = require('../routes/User.Routes');
var Admin = require('../routes/Admin.Routes');

routes.use('/user', User);

routes.use('/admin', Admin);

routes.get('/', function(req, res) {
  res.status(200).json({ message: 'Monitoring API' });
});

module.exports = routes;
