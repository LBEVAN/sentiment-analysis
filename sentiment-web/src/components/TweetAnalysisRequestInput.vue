<template id="tweet-analysis-request-input">
  <form @submit.prevent="submit">
    <p class="mt-3"><b>Enter the link of the tweet to be analysed:</b></p>

    <div class="input-group mb-3">
      <input type="text" class="form-control" id="tweet-link" aria-describedby="basic-addon3" placeholder="https://twitter.com/example/status/1035866523569549313" required v-model="tweetLink">
    </div>

    <button type="submit" class="btn btn-primary">Start Analysis</button>
  </form>
</template>

<script>
import api from '@/api'

export default {
  name: 'TweetAnalysisRequestInput',
  template: 'tweet-analysis-request-input',
  data() {
    return {
      tweetLink: null
    }
  },
  methods: {
    submit: function () {
      api.createTweetAnalysisRequest(this.tweetLink)
        .then((response) => {
          this.$router.push({ name: 'ViewAnalysisRequest', params: { id: response.requestId } })
        })
    }
  }
}
</script>
