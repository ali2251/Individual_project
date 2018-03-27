'use strict';
const request = require('request');
const rp = require('request-promise');
const Link = require('../models/Link');
const cron = require('node-cron');
const os = require('os')
const Influx = require('influx')
let counter = 0;


const influx = new Influx.InfluxDB({
  host: 'localhost',
  database: 'express_response_db',
  schema: [
    {
      measurement: 'response_times',
      fields: {
        latency: Influx.FieldType.INTEGER,
        jitter: Influx.FieldType.INTEGER,
        throughput: Influx.FieldType.INTEGER,
        bandwidth: Influx.FieldType.INTEGER,
        packetloss: Influx.FieldType.INTEGER
      },
      tags: [
        'linkId'
      ]
    }
  ]
})


cron.schedule('* * * * * *', function(){
  ++counter;
  getAndStoreDataInDatabase();
  console.log('running a task every second', "counter: ", counter);
});


function getAndStoreDataInDatabase() {
var links = [];
  rp.post(
      'http://192.168.133.24:8181/restconf/operations/monitoring:get-stats',
      { 'auth': {
      'user': 'admin',
      'pass': 'admin',
      'sendImmediately': false
    },
    json: true

   }).then(function(body) {
          //console.log("here", body)
          let stats = body.output.stats
          //console.log("length of sttas ", stats.length );
          if(stats.length > 2) {
              //check that there are links in the array
            let split = stats.split("}")

            let firstLink = split[0].split(",")

            let link = {
              linkid: 'default',
              bandwidth: 0,
              packetloss: 0,
              latency: 0,
              jitter: 0,
              throughput: 0,
                    date: new Date()
            }

            let firstLinkAttributes = firstLink[0].split("=")[1]
            let linkid = firstLinkAttributes.substring(1,firstLinkAttributes.length-1)
            link.linkid = linkid;
            link.bandwidth = firstLink[1].split("=")[1]
            link.packetloss = firstLink[2].split("=")[1]
            link.latency = firstLink[3].split("=")[1]
            link.jitter = firstLink[4].split("=")[1]
            link.throughput = firstLink[5].split("=")[1]

            //console.log(link);
            links.push(link)

            for(let i = 1; i < split.length-1; ++i) {

              let link1 = {
                linkid: 'default',
                bandwidth: 0,
                packetloss: 0,
                latency: 0,
                jitter: 0,
                throughput: 0,
                date: new Date()
              }

                let current = split[i].split(",")

                let attributes = current[1].split("=")[1]
                let id = attributes.substring(1,attributes.length-1)
                //console.log(id)

                link1.linkid = id;
                link1.bandwidth = firstLink[1].split("=")[1]
                link1.packetloss = firstLink[2].split("=")[1]
                link1.latency = firstLink[3].split("=")[1]
                link1.jitter = firstLink[4].split("=")[1]
                //console.log(firstLink[5].split("=")[0]);
                link1.throughput = firstLink[5].split("=")[1]

                links.push(link1)

            }
          }

          //console.log("links legth before  %%%%%%",links.length,links);
          return new Promise(function(resolve, reject) {
            if(links.length > 0) {
              resolve(links)
            } else {
              reject("Link array is empty");
            }
          });

          //console.log(split[1].split(",")[5].split("=")[0]);
      }).then((res) => {

          //console.log(res, "========================================")
          //console.log(res.length, "  length after");
          if(Array.isArray(res)) {
          //  console.log("I know what i am doing")
            res.forEach((link) => {
            //  console.log(" ------------ ", link);

            influx.writePoints([
              {
                measurement: 'response_times',
                tags: { linkId: link.linkid },
                fields: {
                          latency:link.latency,
                          jitter: link.jitter,
                          throughput:link.throughput,
                          bandwidth:link.bandwidth,
                          packetloss: link.packetloss
                        }
              }
            ]).catch(err => {
              console.error(`Error saving data to InfluxDB! ${err.stack}`)
            })


            })

          }

      }).catch(function(error) {
          console.log("**************");
          console.error(error);
  });

}
