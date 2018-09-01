<template id="tweet-analysis-request-input">
  <form @submit.prevent="submit">
    <p>Enter the link of the tweet to be analysed:</p>

    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <span class="input-group-text" id="basic-addon3">https://twitter.com/</span>
      </div>
      <input type="text" class="form-control" id="tweet-url" aria-describedby="basic-addon3" placeholder="example/status/1035866523569549313" v-model="tweetId">
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
      tweetId: null
    }
  },
  methods: {
    submit: function () {
      api.createTweetAnalysisRequest(this.tweetId)

      // this.$router.push('path-to-go')
    }
  }
}
</script>
