<template>
  <div class="wrapper">
    <h1 class="display-2">SentoNal</h1>
    <h3 class="display-5">The Sentiment Analysis Webapp</h3>

    <div class="section">
      <p>Choose an analysis task:</p>
      <button-toggle-group :values="options" default="" v-on:inputSelected="onOptionChosen"/>
    </div>

    <div id="toReplace">
      <div :is="currentComponent"></div>
      <div v-show="!currentComponent" v-for="component in componentsArray"></div>
    </div>

  </div>
</template>

<script>
import ButtonToggleGroup from '@/components/ButtonToggleGroup'
import PhraseAnalysisRequestInput from '@/components/PhraseAnalysisRequestInput'
import TweetAnalysisRequestInput from '@/components/TweetAnalysisRequestInput'

export default {
  name: 'CreateAnalysisRequest',
  components: {
    'button-toggle-group': ButtonToggleGroup,
    'phrase': PhraseAnalysisRequestInput,
    'tweet': TweetAnalysisRequestInput
  },
  data () {
    return {
      options: [
        {name: 'Phrase', component: 'phrase'},
        {name: 'Twitter - Tweet', component: 'tweet'}
      ],
      chosenOption: null,
      currentComponent: null,
      componentsArray: ['phrase', 'tweet']
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

<style>

</style>
