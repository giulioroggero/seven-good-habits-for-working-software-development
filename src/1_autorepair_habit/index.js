/*
 * Copyright © 2018-present Mia s.r.l.
 * All rights reserved
 */
/* eslint require-await: 0 */
'use strict'

const fastifyMongodb = require('fastify-mongodb')
const customService = require('@mia-platform/custom-plugin-lib')({
  type: 'object',
  required: ['MONGODB_URL'],
  properties: {
    MONGODB_URL: { type: 'string' },
  },
})

module.exports = customService(async function index(service) {
  service.register(fastifyMongodb, {
    url: service.config.MONGODB_URL,
    useNewUrlParser: true,
  }) 

})
