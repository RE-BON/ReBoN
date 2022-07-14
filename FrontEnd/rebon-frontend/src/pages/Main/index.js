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
import Footer from '../../components/Footer';
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

export default function Main({ restCategory, accoCategory, cafeCategory, restData, accoData, cafeData }) {
  const [value, setValue] = React.useState(0);
  const [ready, setReady] = useState(false);
  const [restChecked, setRestChecked] = useState(restCategory[0].id);
  const [accoChecked, setAccoChecked] = useState(accoCategory[0].id);
  const [cafeChecked, setCafeChecked] = useState(cafeCategory[0].id);
  const location = useLocation();

  const getData = () => {
    console.log('=======rest 카테고리 : =========== ', restCategory);
    console.log('=======acco 카테고리 : =========== ', accoCategory);
    console.log('=======cafe 카테고리 : =========== ', cafeCategory);
  };

  useEffect(() => {
    setReady(false);
    getData();
    setReady(true);
  }, [restChecked]);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

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
                        </>
                      ))
                    : restCategory.length}
                </div>
              </div>

              <div className="main-background">
                <div className="searchTitle">📍{location.state.item.name} 식당</div>
              </div>

              <div className="best-wrapper">
                <BestCard data={restData} checked={restChecked} />
              </div>

              <Divider data={restData} checked={restChecked} tagId={location.state.item.id} subId={restCategory[0].id} />
            </TabPanel>
            <TabPanel className="TabPanel" value={value} index={1}>
              <div className="category-wrapper">
                <div class="select-main">
                  {cafeCategory
                    ? cafeCategory.map((cafe, index) => (
                        <>
                          <input type="radio" id={cafe.id} value={cafe.id} name="cafe" checked={cafeChecked === cafe.id} onClick={() => setCafeChecked(cafe.id)} />
                          <label for={cafe.id} className={cafeChecked === cafe.id ? 'radio-click-active' : 'radio-click-stay'}>
                            {cafe.name}
                          </label>
                        </>
                      ))
                    : cafeCategory.length}
                </div>
              </div>

              <div className="main-background">
                <div className="searchTitle">📍{location.state.item.name} 카페</div>
              </div>

              <div className="best-wrapper">
                <BestCard data={cafeData} checked={cafeChecked} />
              </div>

              <Divider data={cafeData} checked={cafeChecked} tagId={location.state.item.id} subId={cafeCategory[0].id} />
            </TabPanel>
            <TabPanel className="TabPanel" value={value} index={2}>
              <div className="category-wrapper">
                <div class="select-main">
                  {accoCategory
                    ? accoCategory.map((acco, index) => (
                        <>
                          <input type="radio" id={acco.id} value={acco.id} name="accomodation" checked={accoChecked === acco.id} onClick={() => setAccoChecked(acco.id)} />
                          <label for={acco.id} className={accoChecked === acco.id ? 'radio-click-active' : 'radio-click-stay'}>
                            {acco.name}
                          </label>
                        </>
                      ))
                    : accoCategory.length}
                </div>
              </div>

              <div className="main-background">
                <div className="searchTitle">📍{location.state.item.name} 숙소</div>
              </div>

              <div className="best-wrapper">
                <BestCard data={accoData} checked={accoChecked} />
              </div>

              <Divider data={accoData} checked={accoChecked} tagId={location.state.item.id} subId={accoCategory[0].id} />
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
}