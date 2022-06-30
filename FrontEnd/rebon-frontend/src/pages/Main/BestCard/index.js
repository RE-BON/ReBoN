import React,{useState,useEffect} from 'react';
import '../../../styles/main.css';
import axios from 'axios';
import { FiHeart } from 'react-icons/fi';
import { FaHeart } from 'react-icons/fa';
import {BsFillBookmarkFill} from 'react-icons/bs'

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

  const [bestInfo,setBestInfo] = useState([
    {
      "id":1,
      "name":"팜스발리",
      "star":0.0,
      "like":true,
      "tags":[{
        "id":1,
        "name":"포항"
      },{
        "id":3,
        "name":"한동대"
      }],
      "image" : "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Supreme_pizza.jpg/800px-Supreme_pizza.jpg"
    },{
      "id":3,
      "name":"한동대 학관",
      "star":0.0,
      "like":false,
      "tags":[
        {
          "id" : 1,
          "name" : "포항"
        },{
          "id":3,
          "name":"한동대"
        }
      ],
      "image" : "https://mblogthumb-phinf.pstatic.net/MjAxOTAxMTNfMTA0/MDAxNTQ3Mzc5NjQ1MzA2.jBPcIF5Tqd8GIrxEQhLG04tIQi33JGLThx4RQuimNVcg.2Nf25aUFMoCd4CdXBwv4HWg8ugDx0Ym9y9nhmEppNE0g.JPEG.let1997/Screenshot_20190113-204020_Naver_Blog.jpg?type=w800"
    }
  ])

  const [like,setLike] = useState(false);

  const likeClick =()=>{
    if(like){
      setLike(false);
    }else{
      setLike(true);
    }
  }

  return (
    <>
      {bestInfo.map((item,idx) => (
      <div className="bestCard"> 
        <img class="best-img"
      src={item.image}/>
        <div className="titleRow-best">
          <div className="placeName-best">{item.name}</div>
          <div className="starNum-best">{item.star}</div>
        </div>

        <div className='likeBtn'>
          {item.like?
          <FaHeart className="heart-icon" md={8} size="22" onClick={likeClick}/>:
          <FiHeart className="heart-icon-fi" md={8} size="22" onClick={likeClick}/>
          }
        </div>
        

        <BsFillBookmarkFill className="bookmark-icon" md={8} size="32"/>
        <div className='best-num'>{idx+1}</div>

        <div className="tagRow-best">
          {item.tags.map((tag)=>(
            <span className="tag-best">{tag.name}</span>
          ))}
        </div>
      </div>
    ))}
  </>
  );
}
