<template id="text-analysis-request-input">
  <form @submit.prevent="submit">
    <p>Enter the text to be analysed:</p>

    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <span class="input-group-text">Text</span>
      </div>
      <textarea class="form-control" aria-label="Text" required v-model="text"></textarea>
    </div>

    <button type="submit" class="btn btn-primary">Start Analysis</button>
  </form>
</template>

<script>
import api from '@/api'

export default {
  name: 'TextAnalysisRequestInput',
  template: 'text-analysis-request-input',
  data () {
    return {
      text: null
    }
  },
  methods: {
    submit: function () {
      api.createTextAnalysisRequest(this.text)
        .then((response) => {
           this.$router.push({ name: 'ViewAnalysisRequest', params: { id: response.requestId } })
        })
    }
  }
}
</script>
