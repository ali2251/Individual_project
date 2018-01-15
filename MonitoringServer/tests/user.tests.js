var should = require('should'); 
var assert = require('assert');
var request = require('supertest');  
var mongoose = require('mongoose');
var winston = require('winston');

describe('Routing', function() {
  var url = 'http://104.236.131.132:3000';
  var tokenId;
  var randomnumber = Math.floor(Math.random() * (0 - 1000 + 1)) + 0;

  before(function(done) {
    // In our tests we use the test db
    mongoose.connect("mongodb://104.236.131.132/mlb");							
    done();
  });
  describe('User', function() {
      var user = {
       name:"Ali",
	   email:"testuser"+randomnumber.toString()+"@mlb.com",
	   password:"apple123",
	   photoURL:"URL",
	   dob:"1:",
	   address:"loc",
	   postcode:"se19ey",
	   phoneNumber:"6043761837",
	   role:"employee",
	   gender:"Male",
	   summary:"Skilled Professional",
	   skillLevel:"Beginner",
	   experience:"2",
	   wage:"30",
	   speciality:"Something"
      };

    it('should return success or duplicate account status code', function(done) {
	    request(url)
		.post('/user/signup')
		.send(user)
		.expect(200)
		.end(function(err, res) {
			if (err || !res || !err && !res) {
		        throw err;
		    } else {
	        	res.should.be.json; 
	        }
	        done();
	    });
    });

    it('should correctly login to an existing account', function(done){
		var body = {
	   		email:user.email,
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

	it('Edit Profile', function() {
		var body = {
	   		email:user.email,
			user: {
				experience: "2"
			}
		};
	    request(url)
		.post('/user/editProfile')
		.send(body)
		.end(function(err,res) {
			if (err || !res || !err && !res) {
				throw err;
			} else {
				res.status.should.be.equal(200);
			}
		})
  	});

	// it('Upload File', function() {
	//     request(url)
	// 	.post('/user/upload')
	// 	.field('name', 'my awesome avatar')
	// 	.attach('avatar', 'test/fixtures/homeboy.jpg')
	// 	.end(function(err,res) {
	// 		if (err || !res || !err && !res) {
	// 			throw err;
	// 		}
	// 		done();
	// 	})
 //  	});

  	it('Get user from JWT Token', function() {
	    request(url)
      	.get('/user/getUserFromJWT')
       	.set('Authorization', tokenId)
	    .set('Accept', 'application/json')
		.end(function(err,res) {
			if (err || !res || !err && !res) {
				throw err;
			} else {
		        res.status.should.be.equal(200);
			}
		})
  	});


  // 	it('Add Card Details', function() {
		// var body = {
	 //   		email: user.email,
		// 	stripeToken: "tok_1A1EPKDnCdBXwOhZ25dkrh99"
		// };
	 //    request(url)
		// .post('/user/addCardDetails')
		// .send(body)
		// .end(function(err,res) {
		// 	if (err || !res || !err && !res) {
		// 		throw err;
		// 	} else {
		// 		res.status.should.be.equal(200);
		// 	}
		// 	done();
		// })
  // 	});


  // 	it('Delete Card Details', function() {
		// var body = {
	 //   		email: user.email,
		// 	stripeToken: "tok_1A1EPKDnCdBXwOhZ25dkrh99"
		// };
	 //    request(url)
		// .delete('/user/deleteCardDetails')
		// .send(body)
		// .expect(200)
		// .end(function(err,res) {
		// 	if (err || !res || !err && !res) {
		// 		throw err;
		// 	} else {
		// 		res.status.should.be.equal(200);
		// 	}
		// 	done();
		// })
  // 	});


  	var body = { email: user.email };
  	//Add in tests for random characters, empty spaces, special characters
  	it('Forgot Password', function() {
  		return request(url)
	      .post('/user/forgotPassword')
	 	  .send(body)
	      .set('Accept', 'application/json')
	      .expect(200)
	      .then(response => {
	          assert(response.body);
	    })

  	});


  });
});