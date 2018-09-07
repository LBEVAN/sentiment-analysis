import axios from 'axios'

const client = axios.create({
  baseURL: 'http://localhost:9200/api',
  json: true,
  headers: {
    "Access-Control-Allow-Origin": "*"
  }
})

export default {

  createTextAnalysisRequest (text) {
    var data = {
      text: text
    }

    return client.post('/request/text', data)
      .then(response => {
        return response.data
      })
      .catch(error => {
        this.handleError(error)
      })
  },

  createTweetAnalysisRequest (tweetLink) {
    var data = {
      tweetLink: tweetLink
    }

    return client.post('/request/tweet', data)
      .then(response => {
        return response.data
      })
      .catch(error => {
        this.handleError(error)
      })
  },

  createHashtagAnalysisRequest (hashtag) {
    var data = {
      hashtag: hashtag
    }

    return client.post('/request/hashtag', data)
      .then(response => {
        return response.data
      })
      .catch(error => {
        this.handleError(error)
      })
  },

  getRequestById (id) {
    return client.get('/request/' + id)
      .then(response => {
        return response.data
      })
      .catch(error => {
        this.handleError(error)
      })
  },

  getResultsByRequestId (id) {
    return client.get('/results/' + id)
      .then(response => {
        return response.data
      })
      .catch(error => {
        this.handleError(error)
      })
  },

  handleError (error) {
    // log the error
    console.log(error)
    clearInterval()

    // first check we actually got a response
    if(error.response == null) {
      // check for networking errors
      if(error.message === 'Network Error') {
        // network error - redirect service error page
        window.location.replace("/service-error");
      }
    } else {
      // now check logical error codes
      if(error.response.status == "404") {
        // redirect to page not found
        window.location.replace("/404");
      } else if(error.response.status == '500') {
        window.location.replace("/service-error")
      } else {
        // allow the client to handle it
        throw error
      }
    }
  }
}
