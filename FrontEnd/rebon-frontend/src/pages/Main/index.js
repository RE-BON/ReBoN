import React from 'react';
import PropTypes from 'prop-types';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import '../../styles/main.css';

import MainCard from './MainCard';
import BestCard from './BestCard';

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

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <div className="tabber-wrapper">
      <Box sx={{ width: '100%' }}>
        <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
          <Tabs className="box" value={value} onChange={handleChange} aria-label="basic tabs example" centered>
            <Tab label="식당" {...a11yProps(0)} />
            <Tab label="카페" {...a11yProps(1)} />
            <Tab label="숙소" {...a11yProps(2)} />
          </Tabs>
        </Box>
        <TabPanel className="TabPanel" value={value} index={0}>
          <div className="category-wrapper">
            <div class="select">
              <input type="radio" id="select-01" name="shop" />
              <label for="select-01">한식</label>
              <input type="radio" id="select-02" name="shop" />
              <label for="select-02">일식</label>
              <input type="radio" id="select-03" name="shop" />
              <label for="select-03">중식</label>
              <input type="radio" id="select-04" name="shop" />
              <label for="select-04">양식</label>
            </div>
          </div>
          <div className="best-wrapper">
            <BestCard />
            <BestCard />
            <BestCard />
            <BestCard />
          </div>
          <div className="divider">
            <div className="searchTitle">'칠포해수욕장'식당</div>
            <select className="filter" name="filter">
              <option value="korean" selected>
                별점순
              </option>
              <option value="english">거리순</option>
            </select>
          </div>

          <div className="mainCard-wrapper">
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
          </div>
        </TabPanel>

        <TabPanel className="TabPanel" value={value} index={1}>
          <div className="best-wrapper">
            <BestCard />
            <BestCard />
            <BestCard />
            <BestCard />
          </div>
          <div className="divider">
            <div className="searchTitle">'칠포해수욕장'식당</div>
            <select className="filter" name="filter">
              <option value="korean" selected>
                별점순
              </option>
              <option value="english">거리순</option>
            </select>
          </div>

          <div className="mainCard-wrapper">
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
          </div>
        </TabPanel>

        <TabPanel className="TabPanel" value={value} index={2}>
          <div className="best-wrapper">
            <BestCard />
            <BestCard />
            <BestCard />
            <BestCard />
          </div>
          <div className="divider">
            <div className="searchTitle">'칠포해수욕장'식당</div>
            <select className="filter" name="filter">
              <option value="korean" selected>
                별점순
              </option>
              <option value="english">거리순</option>
            </select>
          </div>

          <div className="mainCard-wrapper">
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
            <MainCard />
          </div>
        </TabPanel>
      </Box>
    </div>
  );
}
