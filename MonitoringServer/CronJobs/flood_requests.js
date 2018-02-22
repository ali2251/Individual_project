const rp = require('request-promise');

for(var i = 0; i < 100; i++) {

rp.post(
    'http://localhost:8181/restconf/operations/monitoring:install-flow',
    { 'auth': {
    'user': 'admin',
    'pass': 'admin',
    'sendImmediately': false
  },
  json: true

 }).then(function(body) {


 });

 }
