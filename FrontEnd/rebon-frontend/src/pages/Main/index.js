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
import { useLocation } from 'react-router';
import { faClosedCaptioning } from '@fortawesome/free-solid-svg-icons';

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

export default function Main({ restCategory, accoCategory, cafeCategory, restData, accoData, cafeData, restFirstData }) {
  const [value, setValue] = React.useState(0);
  const [ready, setReady] = useState(false);
  const [restChecked, setRestChecked] = useState(restCategory[0].id);
  // const [accoChecked, setAccoChecked] = useState(0);
  // const [cafeChecked, setCafeChecked] = useState(0);

  const [restCategoryList, setRestCategoryList] = useState(); 
  // const [restDataList, setRestDataList] = useState(restData[restCategory[0].id]);

  const location = useLocation();

  const getData = () => {
    console.log("getData");
    console.log("total data: ",restData);
    console.log("data: ",restData[restChecked]);

  }

  useEffect(() => {
    setReady(false);
    // setRestCategoryList(restCategory[restChecked]);
    // setRestDataList( restData[restChecked] );


    getData();

    // if(restData.length > 1){
    //   console.log("data: ",restData[restChecked]);

    // }
   


    // for(var i)
    // if(restData[restChecked].length>0){
    //   restData[restChecked].map()
    // }
    // if(restData[restChecked].length>0){
    //   for(var i = 0; i < restData[restChecked].length; i++){
    //     setRestDataList()
    //   }
    // }

    setReady(true);
    
  }, [restChecked])


  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  // const clickEvent = ()=>{
  //   setRestChecked(0);
  //   setRestChecked(restCategory[0].id);

  // };

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
                          <input type="radio" id={rest.id} value={rest.id} name="restaurant" checked={restChecked === rest.id} onClick={() => setRestChecked(rest.id)} />
                          <label for={rest.id} className={restChecked === rest.id ? 'radio-click-active' : 'radio-click-stay'}>
                            {rest.name}
                          </label>
                          {/* <input type="radio" id={index} value={index} name="restaurant" checked={restChecked === index} onClick={() => setRestChecked(index)} />
                          <label for={index} className={restChecked === index? 'radio-click-active' : 'radio-click-stay'}>
                            {rest.name}
                          </label> */}
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
  
                    {/* <BestCard bestInfo={restData[restChecked]} /> */}
                    <BestCard  restData={restData} checked={restChecked}/>
                    {/* {restDataList ? <BestCard bestInfo={restDataList} /> : clickEvent()} */}
                  </div>
            
                  {/* <Divider shopInfo={restData[restChecked]} tagId={location.state.item.id} subId={restCategory[restChecked].id} /> */}
                  {/* <Divider shopInfo={restDataList} tagId={location.state.item.id} subId={restCategoryList.id} /> */}
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


        </>
      ) : (
        ''
      )}
    </div>
  );

      };