import { observable } from 'mobx';

const mainSetting = observable({
  // state
  restChecked: 0,
  accoChecked: 0,
  cafeChecked: 0,

  // action
  updateRestChecked(restChecked) {
    this.restChecked = restChecked;
  },
  updateAccoChecked(accoChecked) {
    this.accoChecked = accoChecked;
  },
  updateCafeChecked(cafeChecked) {
    this.cafeChecked = cafeChecked;
  },
});

export default mainSetting;
