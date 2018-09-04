import axios from 'axios'

const client = axios.create({
  baseURL: 'http://localhost:9200/api',
  json: true
})

export default {

  createTextAnalysisRequest (text) {
    var data = {
      text: text
    }

    return client.post('/request/text', data)
      .then(response => { return response.data })
  },

  createTweetAnalysisRequest (tweetId) {
    var data = {
      data: tweetId
    }

    return client.post('/request/tweet', data)
      .then(response => { return response.data })
  },

  getRequestById (id) {
    return client.get('/request/' + id)
      .then(response => {
        return response.data
      })
      .catch(error => {
        throw error
      })
  }
}
