import axios from 'axios'

const client = axios.create({
  baseURL: 'http://localhost:9200/api',
  json: true
})

export default {

  getAnalysisRequests () {
    return client.get('/requests/test')
      .then(response => { return response.data })
  },

  getAnalysisOptions () {
    return client.get('/requests/options')
      .then(response => { return response.data })
  },

  createPhraseAnalysisRequest (phrase) {
    var data = {
      data: phrase
    }

    return client.post('/requests/phrase', data)
      .then(response => { return response.data })
  },

  createTweetAnalysisRequest (tweetId) {
    var data = {
      data: tweetId
    }

    return client.post('/requests/tweet', data)
      .then(response => { return response.data })
  }
}
