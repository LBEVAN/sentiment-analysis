<template>
  <div class="wrapper-50">
    <h1 class="display-2">Analysis Results</h1>

    <div class="card text-left">
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
          <div class="row" v-if="index === 0 || index + 1 % 3 === 0">
            <div class="col">
              <p><b>{{ key }}:</b> {{ value }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!--<div v-for="(result, index) in results" :id="getIdWithIndex('', 'accordion-result', index)">-->
      <!--<div class="card">-->
        <!--<div class="card-header">-->
          <!--<h4>Analysis Result {{ index + 1 }}</h4>-->
          <!--<div class="row">-->
            <!--<div class="col">-->
              <!--<p><b>Input:</b> Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus-->
                <!--terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck-->
                <!--quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid-->
                <!--single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes-->
                <!--anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat-->
                <!--craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them-->
                <!--accusamus labore sustainable VHS.-->
              <!--</p>-->
            <!--</div>-->
          <!--</div>-->
          <!--<br/>-->
          <!--<div class="row">-->
            <!--<div clas="col">-->
              <!--<p><b>Request Id:</b> {{ result.requestId }}</p>-->
            <!--</div>-->
          <!--</div>-->
          <!--<div class="row">-->
            <!--<div class="col">-->
              <!--<p class="card-text"><b>Sentiment:</b> {{ result.sentiment }}</p>-->
            <!--</div>-->
            <!--<div class="col">-->
              <!--<p class="card-text"><b>Sentiment Score:</b> {{ result.sentimentScore }}</p>-->
            <!--</div>-->
          <!--</div>-->
          <!--<br/>-->
          <!--<h5 class="text-right">-->
            <!--<button class="btn btn-primary" data-toggle="collapse" :data-target="getIdWithIndex('#', 'collapse-result', index)" aria-expanded="true"-->
                    <!--:aria-controls="getIdWithIndex('', 'collapse-result', index)">-->
              <!--View Breakdown-->
            <!--</button>-->
          <!--</h5>-->
        <!--</div>-->
        <!--<div :id="getIdWithIndex('', 'collapse-result', index)" class="collapse collapsed" :data-parent="getIdWithIndex('#', 'accordion-result', index)">-->
          <!--<div class="card-body">-->
            <!--<div v-for="(sentence, index) in result.sentences">-->
              <!--<div class="row">-->
                <!--<div class="col">-->
                  <!--<p class="card-text"><b>Sentiment:</b> {{ sentence.sentiment }}</p>-->
                <!--</div>-->
                <!--<div class="col">-->
                  <!--<p class="card-text"><b>Sentiment Score:</b> {{ sentence.sentimentValue }}</p>-->
                <!--</div>-->
              <!--</div>-->
            <!--</div>-->
          <!--</div>-->
        <!--</div>-->
      <!--</div>-->
    <!--</div>-->
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
      this.loadData();

      // repeat until data is loaded
      // var interval = setInterval(this.loadData(), 2000);
    },
    methods: {
      loadData: function () {
        console.log("loading")
        api.getRequestById(this.$route.params.id)
          .then((response) => {
            this.analysisRequest = response
          })
          .catch((error) => {
            this.$router.push({ path: '/404' })
          })
      },

      getIdWithIndex: function (prefix, name, index) {
        return prefix + name + '-' + index
      }
    }
  }
</script>
