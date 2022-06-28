import React,{useState,useEffect} from 'react';
import '../../../styles/main.css';
import axios from 'axios';

export default function BestCard() {
  // const [location,setLocation] = useState([]);
  // console.log("props값");
  // console.log(props.location.state);
  // useEffect(() => {
  //   setLocation(props.location.state);
  //   axios
  //     .get('http://34.238.48.93:8080/api/shops?tag=2&category=1&subCategories=5&subCategories=7')
  //     .then((response) => {
  //       console.log("데이터값");
  //       console.log(response.data);
  //     })
  //     .catch((error) => {
  //       console.log('error');
  //     });
  // },[]);

  return (
    <div className="bestCard">
      <img class="best-img"
     src="https://ptsse.co.id/assets/gambar_kategori/images.png"/>
      <div className="titleRow-best">
        <div className="placeName">Place one</div>
        <div className="starNum">4.9</div>
      </div>
      <div className="tagRow-best">
        <span className="tag-best">칠포해수욕장</span>
        <span className="tag-best">칠포읍</span>
      </div>
    </div>
  );
}
