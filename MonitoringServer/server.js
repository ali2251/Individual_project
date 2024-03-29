//dependencies
require('newrelic');
var express = require('express');
path = require('path'),
app = express(),
port = 3000,
bodyParser = require('body-parser');
var mongoose = require('mongoose');
var cookieParser = require('cookie-parser')
var flash = require('connect-flash')
var expressSession = require('express-session');
var logger = require('morgan');
var routes = require('./routes');
var http = require('http').Server(app);
var busboy = require('connect-busboy');
var fs = require('fs');

var key = fs.readFileSync('certificates/domain.key');
var cert = fs.readFileSync( 'certificates/domain.crt' );
var https = require('https');

//var ca = fs.readFileSync( 'certificates/intermediate.crt' );

var options = {
  key: key,
  cert: cert,
  ca: cert
};



function session(req, res, next){
    nodeSession.startSession(req, res, next);
}



//db connection
mongoose.connect('mongodb://localhost/test7');


//middleware
app.use(expressSession({secret: 'mlb'}));
app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});

app.use(express.static(path.join(__dirname, 'public')));
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(cookieParser());
app.use(busboy());


//set views and engines and routing
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');
app.use('/', routes);

app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});
app.use(session);


// error handler
app.use(function(err, req, res, next) {

  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};
  res.status(200).json({ message: err.message });
});


app.listen(port);
//https.createServer(options, app).listen(3000);
console.log("server running on port: "+port)
