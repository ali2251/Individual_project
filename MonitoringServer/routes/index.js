var express = require('express');
var routes = express.Router();


var User = require('../routes/User.Routes');


routes.use('/user', User);

routes.get('/', function(req, res) {
  res.status(200).json({ message: 'Monitoring API' });
});

module.exports = routes;
