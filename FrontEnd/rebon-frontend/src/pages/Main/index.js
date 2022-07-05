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
// import { observable } from 'mobx';

// import * as React from 'react';
import { useObserver } from 'mobx-react';
import indexStore from '../../modules/indexStore';

//useLocation hook
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

export default function Main() {
  const [value, setValue] = React.useState(0);
  const [restCategory, setRestCategory] = useState();
  const [accoCategory, setAccoCategory] = useState();
  const [cafeCategory, setCafeCategory] = useState();
  const [restData, setRestData] = useState();
  const [accoData, setAccoData] = useState();
  const [cafeData, setCafeData] = useState();
  const [ready, setReady] = useState(false);
  const [restChecked, setRestChecked] = useState(0);
  const [cafeChecked, setCafeChecked] = useState(0);
  const [accoChecked, setAccoChecked] = useState(0);

  const { mainSetting } = indexStore();

  const updateRestChecked = (index) => {
    mainSetting.updateRestOption(index);
  };

  const updateRestData = (data) => {
    mainSetting.updateRestData(data);
  };

  const pushRestData = (data) => {
    // mainSetting.pushRestData(data);
  };

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const location = useLocation();
  // console.log(location);

  useLayoutEffect(() => {
    axios
      .get('http://3.34.139.61:8080/api/categories')
      .then((response) => {
        setReady(false);
        setRestCategory(response.data[0].children);
        setAccoCategory(response.data[1].children);
        setCafeCategory(response.data[2].children);

        var restShop = [];
        var accoShop = [];
        var cafeShop = [];

        response.data[0].children.map((rest, index) => {
          var url = 'http://3.34.139.61:8080/api/shops?tag=' + location.state.item.id + '&category=1&subCategories=' + rest.id + '&open=false';
          axios.get(url).then((response) => {
            // restShop.push(response.data);
            pushRestData(response.data);
            console.log('response.data: ', response.data);
            console.log('!!! ', index, ' restData is ', toJS(mainSetting.restData));
            console.log('result: ', toJS(mainSetting.restData));
          });
        });

        response.data[1].children.map((acco, index) => {
          var url = 'http://3.34.139.61:8080/api/shops?tag=' + location.state.item.id + '&category=2&subCategories=' + acco.id + '&open=false';
          axios.get(url).then((response) => {
            accoShop.push(response.data);
          });
        });

        response.data[2].children.map((cafe, index) => {
          var url = 'http://3.34.139.61:8080/api/shops?tag=' + location.state.item.id + '&category=3&subCategories=' + cafe.id + '&open=false';
          axios.get(url).then((response) => {
            cafeShop.push(response.data);
          });
        });

        // setRestData(restShop);
        // var hi = [[11, 22], [{ id: 1, label: 3 }], [44, 55, 66], []];
        // updateRestData(restShop);
        // updateRestData(hi);
        setAccoData(accoShop);
        setCafeData(cafeShop);

        console.log('restShop: ', restShop);

        console.log('117!!, ', mainSetting.restOption);

        console.log('118!!, ', toJS(mainSetting.restData));

        setReady(true);
      })
      .catch((error) => {
        console.log('error');
      });
  }, []);

  return (
    <div className="main-wrapper">
      {ready ? (
        <>
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
                          {/* <input type="radio" id={index} value={rest.id} name="restaurant" checked={restChecked === index} onClick={() => setRestChecked(index)} /> */}
                          <input type="radio" id={index} value={rest.id} name="restaurant" checked={mainSetting.restOption === index} onClick={() => updateRestChecked(index)} />
                          <label for={index} className={mainSetting.restOption === index ? 'radio-click-active' : 'radio-click-stay'}>
                            {rest.name}
                          </label>
                        </>
                      ))
                    : ''}
                </div>
              </div>
              <div className="main-background">
                <div className="searchTitle">📍{location.state.item.name} 식당</div>

                <div className="best-wrapper">
                  {/* restChecked가 바로 안바뀌는 바람에 처음 restChecked가 0에서 1로 눌러서 바껴야 이 데이터도 바껴지는데, 그렇지 않아서 잘못된 데이터가 들어가고 있음. 
                  그래서 restChecked가 바로 바뀔 수 있는 방법은 없을까 생각해보기. */}
                  {/* <BestCard bestInfo={restData[restChecked]} />
                </div>
                <Divider shopInfo={restData[restChecked]} tagId={location.state.item.id} subId={restCategory[restChecked].id} /> */}
                  <BestCard bestInfo={mainSetting.restData[mainSetting.restOption]} />
                </div>
                {/* <Divider shopInfo={mainSetting.restData[mainSetting.restOption]} tagId={location.state.item.id} subId={restCategory[mainSetting.restOption].id} /> */}

                {/* <div className="mainCard-wrapper">
              <MainCard />
            </div> */}
              </div>
            </TabPanel>

            <TabPanel className="TabPanel" value={value} index={1}>
              <div className="category-wrapper">
                <div class="select-main">
                  {cafeCategory
                    ? cafeCategory.map((cafe, index) => (
                        <>
                          <input type="radio" id={index} value={cafe.id} name="cafe" checked={cafeChecked === index} onClick={() => setCafeChecked(index)} />
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
              {/* <Divider shopInfo={cafeData[cafeChecked]} tagId={location.state.item.id} subId={cafeCategory[cafeChecked].id} /> */}

              <div className="mainCard-wrapper">
                <MainCard />
              </div>
            </TabPanel>

            <TabPanel className="TabPanel" value={value} index={2}>
              <div className="category-wrapper">
                <div class="select-main">
                  {accoCategory
                    ? accoCategory.map((acco, index) => (
                        <>
                          <input type="radio" id={index} value={acco.id} name="accommodation" checked={accoChecked === index} onClick={() => setAccoChecked(index)} />
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
              {/* <Divider shopInfo={accoData[accoChecked]} tagId={location.state.item.id} subId={accoCategory[accoChecked].id} /> */}
              <div className="mainCard-wrapper">
                <MainCard />
              </div>
            </TabPanel>
          </Box>
        </>
      ) : (
        ''
      )}
    </div>
  );
}
