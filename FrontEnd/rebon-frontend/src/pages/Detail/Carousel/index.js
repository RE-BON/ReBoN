import React, { useEffect, useState } from 'react';
import Slider from 'react-slick';
import '../../../styles/slick.css';
import '../../../styles/slick-theme.css';
import '../../../styles/detail.css';
import { responsiveFontSizes } from '@mui/material';
import axios from 'axios';

function SampleNextArrow(props) {
  const { className, style, onClick } = props;
  return (
    <div className={className} style={{ ...style, display: 'block', background: '#F5F5F5', borderRadius: '20px', paddingLeft: '18px', paddingTop: '13px' }} onClick={onClick} />
  );
}

function SamplePrevArrow(props) {
  const { className, style, onClick } = props;
  return (
    <div className={className} style={{ ...style, display: 'block', background: '#F5F5F5', borderRadius: '20px', paddingLeft: '16px', paddingTop: '13px' }} onClick={onClick} />
  );
}

function SampleNextArrow_mobile(props) {
  const { className, style, onClick } = props;
  return (
    <div className={className} style={{ ...style, display: 'block', background: '#FFFFFF96', borderRadius: '20px', paddingLeft: '18px', paddingTop: '13px' }} onClick={onClick} />
  );
}

function SamplePrevArrow_mobile(props) {
  const { className, style, onClick } = props;
  return (
    <div className={className} style={{ ...style, display: 'block', background: '#FFFFFF96', borderRadius: '20px', paddingLeft: '16px', paddingTop: '13px' }} onClick={onClick} />
  );
}

export default function Detail({ isMobile, imageInfo, imageLength }) {
  const settings = {
    dots: false,
    infinite: true,
    speed: 500,
    slidesToShow: 2,
    slidesToScroll: 2,
    centerMode: false,
    nextArrow: <SampleNextArrow />,
    prevArrow: <SamplePrevArrow />,
  };
  const settings_mobile = {
    dots: false,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    centerMode: false,
    nextArrow: <SampleNextArrow_mobile />,
    prevArrow: <SamplePrevArrow_mobile />,
  };

  useEffect(() => {
    console.log("Carousel!");
    console.log("image type이 ?  ",typeof imageInfo)

    console.log("images is ",imageInfo, ' length is ',imageInfo.length);
  }, []);

  return (
    <>
      {typeof imageInfo === 'string' ? (
        isMobile === '1' ? (
            <Slider {...settings_mobile} className="detail-slider">
              <div className="slider-first-wrapper">{imageInfo ? <img className="detail-main-image" alt="review-image" src={imageInfo} /> : ''}</div>
            </Slider>
        ) : (
            <Slider {...settings} className="detail-slider">
              <div className="slider-first-wrapper">{imageInfo ? <img className="detail-main-image" alt="review-image" src={imageInfo} /> : ''}</div>
              <div className="slider-second-wrapper">
                <div className="slider-first-sub-wrapper">
                  <div className="slider-first-sub-image-wrapper">{imageInfo ? <img className="detail-sub-image" alt="review-image" src={imageInfo} /> : ''}</div>
                  <div className="slider-second-sub-image-wrapper">{imageInfo ? <img className="detail-sub-image" alt="review-image" src={imageInfo} /> : ''}</div>
                </div>
                <div className="slider-second-sub-wrapper">
                  <div className="slider-first-sub-image-wrapper">{imageInfo ? <img className="detail-sub-image" alt="review-image" src={imageInfo} /> : ''}</div>
                  <div className="slider-second-sub-image-wrapper">{imageInfo ? <img className="detail-sub-image" alt="review-image" src={imageInfo} /> : ''}</div>
                </div>
              </div>
            </Slider>
        )
      ):(
        isMobile === '1' ? (
          imageInfo.length === 1 ? (
            <Slider {...settings_mobile} className="detail-slider">
              <div className="slider-first-wrapper">{imageInfo ? <img className="detail-main-image" alt="review-image" src={imageInfo} /> : ''}</div>
            </Slider>
          ):(
            <Slider {...settings_mobile} className="detail-slider">
              <div className="slider-first-wrapper">{imageInfo ? <img className="detail-main-image" alt="review-image" src={imageInfo} /> : ''}</div>
              <div className="slider-second-wrapper">
                <div className="slider-first-sub-wrapper">
                  <div className="slider-first-sub-image-wrapper">{imageInfo ? <img className="detail-sub-image" alt="review-image" src={imageInfo} /> : ''}</div>
                  <div className="slider-second-sub-image-wrapper">{imageInfo ? <img className="detail-sub-image" alt="review-image" src={imageInfo} /> : ''}</div>
                </div>
                <div className="slider-second-sub-wrapper">
                  <div className="slider-first-sub-image-wrapper">{imageInfo ? <img className="detail-sub-image" alt="review-image" src={imageInfo} /> : ''}</div>
                  <div className="slider-second-sub-image-wrapper">{imageInfo ? <img className="detail-sub-image" alt="review-image" src={imageInfo} /> : ''}</div>
                </div>
              </div>
            </Slider>
          )
        ) : (
          imageInfo.length === 1 ? (
            <Slider {...settings} className="detail-slider">
              <div className="slider-first-wrapper">{imageInfo[0] ? <img className="detail-main-image" alt="review-image" src={imageInfo[0].url} /> : ''}</div>
              <div className="slider-second-wrapper">
                <div className="slider-first-sub-wrapper">
                  <div className="slider-first-sub-image-wrapper">{imageInfo[0] ? <img className="detail-sub-image" alt="review-image" src={imageInfo[0].url} /> : ''}</div>
                  <div className="slider-second-sub-image-wrapper">{imageInfo[0] ? <img className="detail-sub-image" alt="review-image" src={imageInfo[0].url} /> : ''}</div>
                </div>
                <div className="slider-second-sub-wrapper">
                  <div className="slider-first-sub-image-wrapper">{imageInfo[0] ? <img className="detail-sub-image" alt="review-image" src={imageInfo[0].url} /> : ''}</div>
                  <div className="slider-second-sub-image-wrapper">{imageInfo[0] ? <img className="detail-sub-image" alt="review-image" src={imageInfo[0].url} /> : ''}</div>
                </div>
              </div>
            </Slider>
          ):(
            <Slider {...settings} className="detail-slider">
              <div className="slider-first-wrapper">{imageInfo[0] ? <img className="detail-main-image" alt="review-image" src={imageInfo[0].url} /> : ''}</div>
              <div className="slider-second-wrapper">
                <div className="slider-first-sub-wrapper">
                  <div className="slider-first-sub-image-wrapper">{imageInfo[1] ? <img className="detail-sub-image" alt="review-image" src={imageInfo[1].url} /> : ''}</div>
                  <div className="slider-second-sub-image-wrapper">{imageInfo[2] ? <img className="detail-sub-image" alt="review-image" src={imageInfo[2].url} /> : ''}</div>
                </div>
                <div className="slider-second-sub-wrapper">
                  <div className="slider-first-sub-image-wrapper">{imageInfo[3] ? <img className="detail-sub-image" alt="review-image" src={imageInfo[3].url} /> : ''}</div>
                  <div className="slider-second-sub-image-wrapper">{imageInfo[4] ? <img className="detail-sub-image" alt="review-image" src={imageInfo[4].url} /> : ''}</div>
                </div>
              </div>
            </Slider>
          )
        )
      )}
    </>
  );
}

