import { observable } from 'mobx';

const mainSetting = observable({
  // state
  restOption: 777,
  restData: [],

  // action
  updateRestOption(restOption) {
    this.restOption = restOption;
  },
  updateRestData(restData) {
    this.restData = restData;
  },
  pushRestData(restData) {
    // this.restData = this.restData + restData;
    this.restData = restData;
  },
});

export default mainSetting;
