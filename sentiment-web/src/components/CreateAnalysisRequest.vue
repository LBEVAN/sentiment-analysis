<template>
  <div class="wrapper-65">
    <h1 class="display-2">SentoNal</h1>
    <h3 class="display-5">The Sentiment Analysis Webapp</h3>

    <div class="row">

      <div class="col">
        <div class="card mt-4 text-center">
          <h4 class="card-header">Request Analysis</h4>
          <div class="card-body">
            <div class="row">
              <div class="col">
                <p><b>Choose an analysis task:</b></p>
                <button-toggle-group :values="options" default="" v-on:inputSelected="onOptionChosen"/>
                <div :is="currentComponent"></div>
                <div v-show="!currentComponent" v-for="component in componentsArray"></div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="col">
        <search-for-request/>
      </div>
    </div>
  </div>
</template>

<script>
import ButtonToggleGroup from '@/components/ButtonToggleGroup'
import TextAnalysisRequestInput from '@/components/TextAnalysisRequestInput'
import TweetAnalysisRequestInput from '@/components/TweetAnalysisRequestInput'
import HashtagAnalysisRequestInput from '@/components/HashtagAnalysisRequestInput'
import DocumentAnalysisRequestInput from '@/components/DocumentAnalysisRequestInput'
import SearchForRequest from '@/components/SearchForRequest'

export default {
  name: 'CreateAnalysisRequest',
  components: {
    'button-toggle-group': ButtonToggleGroup,
    'search-for-request': SearchForRequest,
    'text-input': TextAnalysisRequestInput,
    'tweet-input': TweetAnalysisRequestInput,
    'hashtag-input': HashtagAnalysisRequestInput,
    'document-input': DocumentAnalysisRequestInput
  },
  data () {
    return {
      options: [
        { name: 'Text', component: 'text-input' },
        { name: 'Twitter - Tweet', component: 'tweet-input' },
        { name: 'Twitter - Hashtag', component: 'hashtag-input' },
        { name: 'Document', component: 'document-input' }
      ],
      chosenOption: null,
      currentComponent: null,
      componentsArray: ['text-input', 'tweet-input', 'hashtag-input', 'document-input']
    }
  },
  methods: {
    onOptionChosen: function (value) {
      this.chosenOption = value
      this.currentComponent = value.component
    }
  }
}
</script>
