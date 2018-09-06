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

    <div class="card mt-4 text-left" :id="getIdWithIndex('', 'result', resultIndex)" v-for="(result, resultIndex) in results">
      <div class="card-header">
        <h4 >Analysis Result {{ resultIndex + 1 }}</h4>
        <div class="row">
          <div class="col">
            <p><b>Text:</b> {{ result.text }}</p>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <p><b>Overall Sentiment:</b> {{ result.sentiment }}</p>
          </div>
          <div class="col">
            <p><b>Overall Sentiment Score:</b> {{ result.sentimentScore }}</p>
          </div>
        </div>
      </div>
      <ul class="list-group list-group-flush">
        <li class="list-group-item sentence" v-for="(sentence, sentenceIndex) in result.sentences" :id="getIdWithIndex('', 'sentence', sentenceIndex)">
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
          <div class="row float-right">
            <button class="btn btn-primary btn-sm" data-toggle="collapse" :data-target="getIdWithIndex('#', 'collapse-sentence', sentenceIndex)" aria-expanded="true"
              :aria-controls="getIdWithIndex('', 'collapse-sentence', sentenceIndex)">
              View Distribution
            </button>
          </div>
          <div :id="getIdWithIndex('', 'collapse-sentence', sentenceIndex)" class="row collapse collapsed" :data-parent="getIdWithIndex('#', 'result', resultIndex)">
            <div class="col">
              <sentiment-distribution-chart :id="getIdWithIndex('', 'pie', sentenceIndex)" :values="sentence.sentimentDistribution"/>
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  import api from '@/api'
  import SentimentDistributionChart from '@/components/SentimentDistributionChart'

  export default {
    name: 'ViewAnalysisRequest',
    components: {
      'sentiment-distribution-chart': SentimentDistributionChart
    },
    data: function () {
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
      // setInterval(() => this.intervalLoad, 3000)
      this.interval = setInterval(() => this.intervalLoad(), 3000)
    },
    computed: {
      isDataLoaded: function () {
        if(this.analysisRequest == null || ((this.results == null) && this.analysisRequest.status != 'Failed')) {
          return false
        } else {
          return true
        }
      }
    },
    methods: {
      intervalLoad: function () {
        console.log('intervalLoad')
        if(this.isDataLoaded) {
          clearInterval(this.interval)
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
              if(response.length !== 0) {
                this.results = response
              }
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

<style>
  .sentence {
    font-size: 0.75em;
  }
</style>
