let request = require('request');
let rp = require('request-promise');



links = [];

rp.post(
    'http://localhost:8181/restconf/operations/monitoring:get-stats',
    { 'auth': {
    'user': 'admin',
    'pass': 'admin',
    'sendImmediately': false
  },
  json: true

 }).then(function(body) {
        console.log("here")
        let stats = body.output.stats
        let split = stats.split("}")

        let firstLink = split[0].split(",")

        let link = {
          linkid: 'default',
          bandwidth: 0,
          packetloss: 0,
          latency: 0,
          jitter: 0,
          throughput: 0
        }

        let firstLinkAttributes = firstLink[0].split("=")[1]
        let linkid = firstLinkAttributes.substring(1,firstLinkAttributes.length-1)
        link.linkid = linkid;
        link.bandwidth = firstLink[1].split("=")[1]
        link.packetloss = firstLink[2].split("=")[1]
        link.latency = firstLink[3].split("=")[1]
        link.jitter = firstLink[4].split("=")[1]
        link.throughput = firstLink[5].split("=")[1]

        console.log(link);
        links.push(link)

        for(let i = 1; i < split.length-1; ++i) {

          let link1 = {
            linkid: 'default',
            bandwidth: 0,
            packetloss: 0,
            latency: 0,
            jitter: 0,
            throughput: 0
          }

            let current = split[i].split(",")

            let attributes = current[1].split("=")[1]
            let id = attributes.substring(1,attributes.length-1)
            console.log(id)

            link1.linkid = id;
            link1.bandwidth = firstLink[1].split("=")[1]
            link1.packetloss = firstLink[2].split("=")[1]
            link1.latency = firstLink[3].split("=")[1]
            link1.jitter = firstLink[4].split("=")[1]
            console.log(firstLink[5].split("=")[0]);
            link1.throughput = firstLink[5].split("=")[1]

            links.push(link1)

        }

        console.log(links);

        //console.log(split[1].split(",")[5].split("=")[0]);
    }
).catch(function(error) {
  console.log("**************");
  console.error(error);
});




// rp.post(
//     'http://localhost:8181/restconf/operations/monitoring:get-stats',
//     { 'auth': {
//     'user': 'admin',
//     'pass': 'admin',
//     'sendImmediately': false
//   } },
//     function (error, response, body) {
//         if (!error && response.statusCode == 200) {
//             console.log(body)
//         }
//         console.error();
//     }
// );
