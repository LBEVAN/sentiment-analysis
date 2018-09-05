<template>
  <div class="wrapper-50">
    <h1 class="display-2">Analysis Results</h1>

    <div class="row">
      <div class="col">
        <form @submit.prevent="returnToHomepage">
          <button type="submit" class="btn btn-primary">Return to Homepage</button>
        </form>
      </div>
    </div>

    <div class="card mt-4 text-left" v-if="analysisRequest !== null">
      <h4 class="card-header">Analysis Request Details</h4>

      <div class="card-body">
        <div class="row">
          <div class="col">
            <p><b>Request Id:</b> {{ analysisRequest.requestId }}</p>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <p><b>Request Status:</b> {{ analysisRequest.status }}</p>
          </div>
          <div class="col">
            <p><b>Date Requested:</b> {{ analysisRequest.dateRequested }}</p>
          </div>
        </div>
        <div v-for="(value, key, index) in analysisRequest.attributes">
          <div class="row">
            <div class="col">
              <p><b>{{ key }}:</b> {{ value }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="card mt-4" v-if="!isDataLoaded">
      <div class="card-body">
        <div class="row">
          <div class="col">
            <p><b>Your request is being processed. The results will be loaded automatically.</b></p>
          </div>
        </div>
      </div>
    </div>

    <div class="card mt-4 text-left" v-for="(result, index) in results">
      <div class="card-header">
        <h4 >Analysis Result {{ index + 1 }}</h4>
        <p><b>Text:</b> {{ result.text }}</p>
      </div>
      <ul class="list-group list-group-flush">
        <li class="list-group-item" v-for="(sentence, index) in result.sentences">
          <div class="row">
            <div class="col">
              <p>{{ sentence.text }}</p>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <p><b>Sentiment:</b> {{ sentence.sentiment }}</p>
            </div>
            <div class="col">
              <p><b>Sentiment Score:</b> {{ sentence.sentimentScore }}</p>
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  import api from '@/api'

  export default {
    name: 'ViewAnalysisRequest',
    data() {
      return {
        analysisRequest: null,
        results: null
      }
    },
    mounted() {
      // initial data load
      this.loadData()

      // set up a continuous call until the data
      // is loaded or the request has failed
      setInterval(() => this.intervalLoad(), 3000)
    },
    computed: {
      isDataLoaded: function () {
        if(this.analysisRequest === null || (this.results === null && this.analysisRequest.status !== 'Failed')) {
          return false
        } else {
          return true
        }
      }
    },
    methods: {
      intervalLoad: function () {
        if(this.isDataLoaded) {
          clearInterval()
        } else {
          this.loadData()
        }
      },

      loadData: function () {
        console.log("Loading data.....")
        if(this.analysisRequest === null) {
          api.getRequestById(this.$route.params.id)
            .then((response) => {
              this.analysisRequest = response
            })
        }

        if(this.results === null) {
          api.getResultsByRequestId(this.$route.params.id)
            .then((response) => {
              this.results = response
            })
        }
      },

      getIdWithIndex: function (prefix, name, index) {
        return prefix + name + '-' + index
      },

      returnToHomepage: function () {
        this.$router.push({ name: 'CreateAnalysisRequest'})
      }
    }
  }
</script>
