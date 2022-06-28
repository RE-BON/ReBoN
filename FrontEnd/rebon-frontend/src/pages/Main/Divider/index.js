import React,{useState} from 'react';
import '../../../styles/main.css';
import Toggle from 'react-toggle';

export default function Divider() {
  const [toggleOn, setToggleOn] = useState(false);

  const toggleChange = (event) => {
    if (toggleOn) setToggleOn(false);
    else setToggleOn(true);
    // console.log(event);
  };
  return (
    <div className="divider-main">
      <div className="searchTitle">'칠포해수욕장'식당</div>
      <div>
        <Toggle className="review-toggle" id="cheese-status" defaultChecked={toggleOn} onChange={toggleChange} />
        <div>현재 영업중</div>
        <select className="filter" name="filter">
          <option value="star" selected>
            별점순
          </option>
          <option value="review">리뷰많은 순</option>
          <option value="recent">최신순</option>
        </select>
      </div>
    </div>
  );
}
