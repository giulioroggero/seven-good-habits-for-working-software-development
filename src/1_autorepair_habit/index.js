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

  service.addRawCustomPlugin('GET', '/hello', async function handler(req) {
    return `Hello ${req.getUserId() || req.query.who}`
  }, {
    querystring: {
      type: 'object',
      properties: {
        who: {
          type: 'string',
          default: 'World',
        },
      },
    },
  })

  service.addRawCustomPlugin('POST', '/hello', async function handler(req) {
    const currentUserId = req.getUserId()
    if (!currentUserId) {
      throw new Error('This API should be consumed only by authenticated users!')
    }
    const userToSayHello = req.body.who
    return `${currentUserId} say "Hello!" to ${userToSayHello}`
  }, {
    body: {
      type: 'object',
      required: ['who'],
      properties: {
        who: {
          type: 'string',
        },
      },
    },
  })

  service.decorate('GREETING_TYPE', {
    HELLO: 'hello',
  })
  service.addRawCustomPlugin('POST', '/some-db-op', async function handler(req, reply) {
    const currentUserId = req.getUserId()
    if (!currentUserId) {
      throw new Error('This API should be consumed only by authenticated users!')
    }
    const userToSayHello = req.body.who

    this.mongo.db.collection('my-collection').insertOne({
      from: currentUserId,
      to: userToSayHello,
      type: this.GREETING_TYPE.HELLO,
    })

    reply.code(204)
  }, {
    body: {
      type: 'object',
      required: ['who'],
      properties: {
        who: {
          type: 'string',
        },
      },
    },
  })
})
