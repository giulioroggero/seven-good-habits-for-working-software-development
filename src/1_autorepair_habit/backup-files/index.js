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

  service.addRawCustomPlugin('GET', '/cars', async function handler(req) {
    return this.mongo.db.collection('cars').find({}).toArray()
  })

  service.addRawCustomPlugin('POST', '/cars', async function handler(req, reply) {
    this.mongo.db.collection('cars').insertOne({
      model: req.body.model,
      color: req.body.color,
      quantity: req.body.quantity,
    })

    reply.code(204)
  })

})

// eslint-disable-next-line no-unused-vars
module.exports.healthinessHandler = function healthinessHandler(fastify) {
  const statusOK = fastify.mongo.client && fastify.mongo.client.isConnected()
  const checkupResponse = statusOK ? { statusOK } : { statusOK, message: 'Mongo status is unhealthy' }

  return checkupResponse
}

