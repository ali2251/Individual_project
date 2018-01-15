var should = require('should'); 
var assert = require('assert');
var request = require('supertest');  
var mongoose = require('mongoose');
var winston = require('winston');

describe('Routing', function() {
  var url = 'http://104.236.131.132:3000';
  var tokenId;

  // within before() you can run all the operations that are needed to setup your tests. In this case
  // I want to create a connection with the database, and when I'm done, I call done().
  before(function(done) {
    // In our tests we use the test db
    mongoose.connect("mongodb://104.236.131.132/mlb");							
    done();
  });
  describe('Search', function() {

    it('should correctly login to an existing account', function(done){
	var body = {
   		email:"testuser53@mlb.com",
		password:"apple123"
	};
	request(url)
		.post('/user/login')
		.send(body)
		.expect(200) //Status code
		.end(function(err,res) {
			if (err || !res || !err && !res) {
				throw err;
			} else {
				res.body.should.have.property('token');
				res.body.should.have.property('email');
				
				tokenId = res.body.token;
				request(url)
					.post('/user/checkAuthorisation')
	      			.set('Authorization', tokenId)
					.expect('Content-Type', /json/)
					.expect(200) //Status code
					.end(function(err,res) {
						if (err || !res || !err && !res) {
							throw err;
						} else {}
						done();
					});
			}
	
		});
	});

    it('Search Regular', function(done) {
    request(url)
	.get('/search/search?postcode=sw163ex')
	.expect(200)
	.end(function(err, res) {
       if (err || !res || !err && !res) {
          throw err;
        } else {
          	res.should.be.json;
          	res.should.be.array;
        }
        done();
       });
    });
    it('Search by JWT', function(done){
	request(url)
		.post('/search/searchByJWT')
		.set('Authorization', tokenId)
		.expect('Content-Type', /json/)
		.expect(200)
		.end(function(err,res) {
	        if (err || !res || !err && !res) {
	          throw err;
	        }
			done();
		});
	});
	

	it('Get All Adverts', function(done){
	request(url)
		.get('/advert/getAllAdverts')
		.expect(200) //Status code
		.end(function(err,res) {
			if (err || !res || !err && !res) {
	          throw err;
	        } else {
				res.should.be.json;
	        }
			done();
		});
	});

	it('Search by Adverts', function(done){
	request(url)
		.get('/search/searchByAdvert?id=58d826e8767c2348cc25c17a')
		.expect(200) //Status code
		.end(function(err,res) {
			if (err || !res || !err && !res) {
	          throw err;
	        } else {
				res.should.be.json;
			}
			done();
		});
	});
  });
});