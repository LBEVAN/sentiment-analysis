<template id="document-analysis-request-input">
  <form @submit.prevent="submit">
    <p class="mt-3"><b>Choose a document to be analysed:</b></p>

    <div class="input-group mb-3">
      <input id="document" type="file" class="form-control-file btn" required ref="document" v-on:change="onSelectDocument()">
    </div>

    <button type="submit" class="btn btn-primary">Start Analysis</button>
  </form>
</template>

<script>
  import api from '@/api'

  export default {
    name: 'DocumentAnalysisRequestInput',
    template: 'document-analysis-request-input',
    data() {
      return {
        document: ''
      }
    },
    methods: {
      onSelectDocument: function () {
        this.document = this.$refs.document.files[0];
      },

      submit: function () {
        let formData = new FormData();
        formData.append('document', this.document);

        api.createDocumentAnalysisRequest(formData)
          .then((response) => {
            this.$router.push({ name: 'ViewAnalysisRequest', params: { id: response.requestId } })
          })
      }
    }
  }
</script>
