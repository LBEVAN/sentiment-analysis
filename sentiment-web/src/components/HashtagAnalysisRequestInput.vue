<template id="hashtag-analysis-request-input">
  <form @submit.prevent="submit">
    <p class="mt-3"><b>Enter the hashtag of the tweets to be analysed:</b></p>

    <div class="input-group mb-3">
      <input type="text" class="form-control" id="hashtag" aria-describedby="basic-addon3" placeholder="#example" required v-model="hashtag">
    </div>

    <button type="submit" class="btn btn-primary">Start Analysis</button>
  </form>
</template>

<script>
  import api from '@/api'

  export default {
    name: 'HashtagAnalysisRequestInput',
    template: 'hashtag-analysis-request-input',
    data() {
      return {
        hashtag: null
      }
    },
    methods: {
      submit: function () {
        api.createHashtagAnalysisRequest(this.hashtag)
          .then((response) => {
            this.$router.push({ name: 'ViewAnalysisRequest', params: { id: response.requestId } })
          })
      }
    }
  }
</script>
