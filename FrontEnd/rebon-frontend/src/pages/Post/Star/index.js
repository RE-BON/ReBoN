import '../../../styles/post-star.css';
import React, { useState } from 'react';
export default function Star(props) {
  const [pharase, setPharase] = useState(null);
  let state = pharase;
  const [starRate, setStarRate] = useState(null);
  // const sendTextValue = (props) => {
  //   console.log(11111111111);
  //   props.getStarRate(starRate);
  // };

  return (
    <form name="myform" id="myform" method="post" action="./save">
      <div className="star-wrapper">
        <fieldset>
          <input
            onClick={() => {
              setPharase('최고예요.');
              setStarRate(4);
            }}
            type="radio"
            name="rating"
            value="5"
            id="rate1"
          />
          <label htmlFor="rate1">★</label>
          <input
            onClick={() => {
              setPharase('좋아요.');
              setStarRate(3);
            }}
            type="radio"
            name="rating"
            value="4"
            id="rate2"
          />
          <label htmlFor="rate2">★</label>
          <input
            onClick={() => {
              setPharase('괜찮아요.');
              setStarRate(2);
            }}
            type="radio"
            name="rating"
            value="3"
            id="rate3"
          />
          <label htmlFor="rate3">★</label>
          <input
            onClick={() => {
              setPharase('그저 그래요.');
              setStarRate(1);
            }}
            type="radio"
            name="rating"
            value="2"
            id="rate4"
          />
          <label htmlFor="rate4">★</label>
          <input
            onClick={() => {
              setPharase('별로예요.');
              setStarRate(0);
            }}
            type="radio"
            name="rating"
            value="1"
            id="rate5"
          />
          <label htmlFor="rate5">★</label>
        </fieldset>
        <div className="star-wrapper-pharases">{state == null ? null : <span>{pharase}</span>}</div>
      </div>
    </form>
  );
}
