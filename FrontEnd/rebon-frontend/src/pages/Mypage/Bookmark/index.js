import BookmarkCard from './BookmarkCard';
import '../../../styles/bookmark.css';

import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import axios from 'axios';

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

export default function Bookmark() {
  const [value, setValue] = React.useState(0);
  const [ready, setReady] = useState(false);
  const [restData, setRestData] = useState();
  const [accoData, setAccoData] = useState();
  const [cafeData, setCafeData] = useState();
  const token = window.sessionStorage.getItem('token');

  const location = useLocation();
  const getData = () => {
    // console.log('=======rest 카테고리 : =========== ', restCategory);
    // console.log('=======acco 카테고리 : =========== ', accoCategory);
    // console.log('=======cafe 카테고리 : =========== ', cafeCategory);
  };

  useEffect(() => {
    var restUrl = 'http://3.34.139.61:8080/api/shops/likes?categoryId=1&page=1&size=2';
    var accoUrl = 'http://3.34.139.61:8080/api/shops/likes?categoryId=2&page=1&size=1';
    var cafeUrl = 'http://3.34.139.61:8080/api/shops/likes?categoryId=3&page=1&size=1';

    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };
    if (token) {
      axios.get(restUrl, config).then((response) => {
        setRestData(response.data);
        console.log('식당 좋아요!!');
        console.log(response);
      });

      axios.get(accoUrl, config).then((response) => {
        setAccoData(response.data);
      });

      axios.get(cafeUrl, config).then((response) => {
        setCafeData(response.data);
      });
    }
  }, []);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  return (
    <>
      <div className="bookmark-container">
        <div className="bookmark-title">찜목록</div>
        <div className="bookmark-wrapper">
          <Box sx={{ width: '100%' }}>
            <Box sx={{ borderBottom: 1, width: '55.5%', marginLeft: '21.5%' }}>
              <Tabs
                className="box"
                value={value}
                TabIndicatorProps={{
                  style: {
                    backgroundColor: '#ff6b6c',
                    marginLeft: '1.7em',
                    width: '2.3em',
                    bottom: '25%',
                    zIndex: '-1',
                  },
                }}
                onChange={handleChange}
                aria-label="basic tabs example"
                centered
              >
                <Tab label="식당" {...a11yProps(0)} />
                <Tab label="카페" {...a11yProps(1)} />
                <Tab label="숙소" {...a11yProps(2)} />
              </Tabs>
            </Box>
            <TabPanel class="TabPanel-bookmark" value={value} index={0}>
              <div className="boookmarkCard-wrapper">
                <BookmarkCard data={restData} />
                {/* <BookmarkCard /> */}
              </div>
            </TabPanel>

            <TabPanel class="TabPanel-bookmark" value={value} index={1}>
              <div className="boookmarkCard-wrapper">
                <BookmarkCard data={accoData} />
              </div>
            </TabPanel>

            <TabPanel class="TabPanel-bookmark" value={value} index={2}>
              <div className="mainCard-wrapper">
                <BookmarkCard data={cafeData} />
              </div>
            </TabPanel>
          </Box>
        </div>
      </div>

      {/* /////////////////////////////////// 모바일 버전 ///////////////////////////////////////// */}

      <div className="bookmark-container-mobile" hidden>
        <div className="bookmark-wrapper">
          <Box sx={{ width: '100%' }}>
            <Box sx={{ borderBottom: 1, width: '55.5%', marginLeft: '21.5%' }}>
              <Tabs
                className="box"
                value={value}
                TabIndicatorProps={{
                  style: {
                    backgroundColor: '#ff6b6c',
                    marginLeft: '1.7em',
                    width: '2.3em',
                    bottom: '25%',
                    zIndex: '-1',
                  },
                }}
                onChange={handleChange}
                aria-label="basic tabs example"
                centered
              >
                <Tab label="식당" {...a11yProps(0)} />
                <Tab label="카페" {...a11yProps(1)} />
                <Tab label="숙소" {...a11yProps(2)} />
              </Tabs>
            </Box>
            <TabPanel className="TabPanel-bookmark" value={value} index={0}>
              <div className="category-wrapper"></div>
              <div className="best-wrapper"></div>

              <div className="mainCard-wrapper">
                <BookmarkCard data={restData} />
              </div>
            </TabPanel>
            <TabPanel className="TabPanel-bookmark" value={value} index={1}>
              <div className="category-wrapper"></div>
              <div className="best-wrapper"></div>

              <div className="mainCard-wrapper">
                <BookmarkCard data={accoData} />
              </div>
            </TabPanel>
            <TabPanel className="TabPanel-bookmark" value={value} index={2}>
              <div className="category-wrapper"></div>
              <div className="best-wrapper"></div>

              <div className="mainCard-wrapper">
                <BookmarkCard data={cafeData} />
              </div>
            </TabPanel>
          </Box>
        </div>
      </div>
    </>
  );
}
