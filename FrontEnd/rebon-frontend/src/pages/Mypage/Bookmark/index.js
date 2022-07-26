import BookmarkCard from './BookmarkCard';
import '../../../styles/bookmark.css';

import React from 'react';
import PropTypes from 'prop-types';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';

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

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  return (
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
          <TabPanel value={value} index={0}>
            <div className="category-wrapper"></div>
            <div className="best-wrapper"></div>

            <div className="mainCard-wrapper">
              <BookmarkCard />
              <BookmarkCard />
              <BookmarkCard />
              <BookmarkCard />
              <BookmarkCard />
              <BookmarkCard />
              <BookmarkCard />
            </div>
          </TabPanel>

          <TabPanel className="TabPanel" value={value} index={1}>
            <div className="mainCard-wrapper">
              <BookmarkCard />
              <BookmarkCard />
              <BookmarkCard />
              <BookmarkCard />
              <BookmarkCard />
              <BookmarkCard />
              <BookmarkCard />
            </div>
          </TabPanel>

          <TabPanel className="TabPanel" value={value} index={2}>
            <div className="mainCard-wrapper">
              <BookmarkCard />
              <BookmarkCard />
              <BookmarkCard />
              <BookmarkCard />
              <BookmarkCard />
              <BookmarkCard />
              <BookmarkCard />
            </div>
          </TabPanel>
        </Box>
      </div>
    </div>
  );
}
