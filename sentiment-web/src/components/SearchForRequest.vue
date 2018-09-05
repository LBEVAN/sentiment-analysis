<template>
  <form @submit.prevent="submit">
    <div class="card mt-4 text-center">
      <h4 class="card-header">Search for Request</h4>
      <div class="card-body">
        <div class="row">
          <div class="col">
            <div class="input-group mb-3">
              <input type="text" class="form-control" id="tweet-url" aria-describedby="basic-addon3" placeholder="94482c22-3240-471b-8b27-ffe8755ba4e3" required v-model="requestId">
            </div>
            <button type="submit" class="btn btn-primary">Search</button>
          </div>
        </div>
      </div>
    </div>
  </form>
</template>

<script>
  import api from '@/api'

  export default {
    name: 'SearchForRequest',
    data () {
      return {
        requestId:  null
      }
    },
    methods: {
      submit: function () {
        api.getRequestById(this.requestId)
          .then((response) => {
            this.$router.push({ name: 'ViewAnalysisRequest', params: { id: response.requestId } })
          })
      }
    }
  }

</script>
