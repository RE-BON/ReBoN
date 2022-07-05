import React, { useState, useEffect, useLayoutEffect } from 'react';
import PropTypes from 'prop-types';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import '../../styles/main.css';

import MainCard from './MainCard';
import BestCard from './BestCard';
import Divider from './Divider';
import Header from '../../components/Header';
import axios from 'axios';
import { toJS } from 'mobx';
import { observable } from 'mobx';
import { observer } from 'mobx-react';
import { useObserver } from 'mobx-react';
import indexStore from '../../modules/indexStore';
import { useLocation } from 'react-router';

function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <div role="tabpanel" hidden={value !== index} id={`simple-tabpanel-${index}`} aria-labelledby={`simple-tab-${index}`} {...other}>
      {value === index && (
        <Box sx={{ p: 3 }}>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
}

function a11yProps(index) {
  return {
    id: `simple-tab-${index}`,
    'aria-controls': `simple-tabpanel-${index}`,
  };
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
};

export default function Main({ restCategory, accoCategory, cafeCategory, restData, accoData, cafeData }) {
  const [value, setValue] = React.useState(0);
  const [ready, setReady] = useState(false);
  const [restChecked, setRestChecked] = useState(0);
  const [accoChecked, setAccoChecked] = useState(0);
  const [cafeChecked, setCafeChecked] = useState(0);

  const location = useLocation();

  const { mainSetting } = indexStore();

  // const restChecked = observable(0);
  // const accoChecked = observable(0);
  // const cafeChecked = observable(0);

  const updateRestChecked = (index) => {
    console.log('!!!: ', index);
    mainSetting.updateRestChecked(index);
    console.log('result is ', mainSetting.restChecked);

    // checkedData = {
    //   restChecked: index,
    //   accoChecked: accoChecked,
    //   cafeChecked: cafeChecked,
    // };
  };
  const updateAccoChecked = (index) => {
    mainSetting.updateAccoChecked(index);

    // accoChecked = index;
    // checkedData = {
    //   restChecked: restChecked,
    //   accoChecked: index,
    //   cafeChecked: cafeChecked,
    // };
  };
  const updateCafeChecked = (index) => {
    mainSetting.updateCafeChecked(index);

    // checkedData = {
    //   restChecked: restChecked,
    //   accoChecked: accoChecked,
    //   cafeChecked: index,
    // };
  };

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  useLayoutEffect(() => {
    console.log('main~~');
    console.log('restCate: ', restCategory);
    console.log('restData: ', restData);
  }, []);

  return (
    <div className="main-wrapper">
      {/* {ready ? ( */}
      {/* <> */}
      <Header />
      <Box sx={{ width: '100%' }}>
        <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
          <Tabs
            TabIndicatorProps={{
              style: {
                backgroundColor: '#ff6b6c',
              },
            }}
            className="box"
            value={value}
            onChange={handleChange}
            aria-label="basic tabs example"
            centered
          >
            <Tab label="식당" {...a11yProps(0)} />
            <Tab label="카페" {...a11yProps(1)} />
            <Tab label="숙소" {...a11yProps(2)} />
          </Tabs>
        </Box>
        <TabPanel className="TabPanel" value={value} index={0}>
          <div className="category-wrapper">
            <div class="select-main">
              {restCategory
                ? restCategory.map((rest, index) => (
                    <>
                      {/* <input type="radio" id={index} value={rest.id} name="restaurant" checked={mainSetting.restChecked === index} onClick={() => updateRestChecked(index)} /> */}
                      <input type="radio" id={index} value={rest.id} name="restaurant" checked={mainSetting.restChecked === index} onClick={() => setRestChecked(index)} />
                      {/* <label for={index} className={mainSetting.restChecked === index ? 'radio-click-active' : 'radio-click-stay'}> */}
                      <label for={index} className={restChecked === index ? 'radio-click-active' : 'radio-click-stay'}>
                        {rest.name}
                      </label>
                    </>
                  ))
                : restCategory.length}
            </div>
          </div>
          {/* restChecked가 바로 안바뀌는 바람에 처음 restChecked가 0에서 1로 눌러서 바껴야 이 데이터도 바껴지는데, 그렇지 않아서 잘못된 데이터가 들어가고 있음. 
                  그래서 restChecked가 바로 바뀔 수 있는 방법은 없을까 생각해보기. */}
          <div className="main-background">
            <div className="searchTitle">📍{location.state.item.name} 식당</div>
          </div>

          <div className="best-wrapper">
            {/* <BestCard bestInfo={restData[mainSetting.restChecked]} /> */}
            <BestCard bestInfo={restData[restChecked]} />
          </div>
          {/* <Divider shopInfo={restData[mainSetting.restChecked]} tagId={location.state.item.id} subId={restCategory[mainSetting.restChecked].id} /> */}
          <Divider shopInfo={restData[restChecked]} tagId={location.state.item.id} subId={restCategory[restChecked].id} />
          <div className="mainCard-wrapper">
            <MainCard />
          </div>
        </TabPanel>

        <TabPanel className="TabPanel" value={value} index={1}>
          {/* <div className="category-wrapper">
            <div class="select-main">
              {cafeCategory
                ? cafeCategory.map((cafe, index) => (
                    <>
                      <input type="radio" id={index} value={cafe.id} name="cafe" checked={cafeChecked === index} onClick={() => updateCafeChecked(index)} />
                      <label for={index} className={cafeChecked === index ? 'radio-click-active' : 'radio-click-stay'}>
                        {cafe.name}
                      </label>
                    </>
                  ))
                : ''}
            </div>
          </div>
          <div className="best-wrapper">
            <BestCard bestInfo={cafeData[cafeChecked]} />
          </div>
          <Divider shopInfo={cafeData[cafeChecked]} tagId={location.state.item.id} subId={cafeCategory[cafeChecked].id} />

          <div className="mainCard-wrapper">
            <MainCard />
          </div> */}
        </TabPanel>

        <TabPanel className="TabPanel" value={value} index={2}>
          {/* <div className="category-wrapper">
            <div class="select-main">
              {accoCategory
                ? accoCategory.map((acco, index) => (
                    <>
                      <input type="radio" id={index} value={acco.id} name="accommodation" checked={accoChecked === index} onClick={() => updateAccoChecked(index)} />
                      <label for={index} className={accoChecked === index ? 'radio-click-active' : 'radio-click-stay'}>
                        {acco.name}
                      </label>
                    </>
                  ))
                : ''}
            </div>
          </div>
          <div className="best-wrapper">
            <BestCard bestInfo={accoData[accoChecked]} />
          </div>
          <Divider shopInfo={accoData[accoChecked]} tagId={location.state.item.id} subId={accoCategory[accoChecked].id} />
          <div className="mainCard-wrapper">
            <MainCard />
          </div> */}
        </TabPanel>
      </Box>
      {/* </> */}
      {/* ) : (
        ''
      )} */}
    </div>
  );
}
