const mongoose = require('mongoose');

const Link = mongoose.model(
  'Link',
  {
    id: String,
    bandwidth:   [Number],
    packetloss:  [Number],
    latency:     [Number],
    jitter:      [Number],
    throughput:  [Number],
    date:       [Date]
  });

module.exports = Link;
