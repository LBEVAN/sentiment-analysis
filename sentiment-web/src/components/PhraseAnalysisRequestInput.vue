<template id="phrase-analysis-request-input">
  <form @submit.prevent="submit">
    <p>Enter the phrase to be analysed:</p>

    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <span class="input-group-text">Phrase</span>
      </div>
      <textarea class="form-control" aria-label="Phrase" v-model="phrase"></textarea>
    </div>

    <button type="submit" class="btn btn-primary">Start Analysis</button>
  </form>
</template>

<script>
import api from '@/api'

export default {
  name: 'PhraseAnalysisRequestInput',
  template: 'phrase-analysis-request-input',
  data () {
    return {
      phrase: null
    }
  },
  methods: {
    submit: function () {
      api.createPhraseAnalysisRequest(this.phrase)

      // this.$router.push('path-to-go')
    }
  }
}
</script>
