import '../../../styles/post-star.css';
import React, { useEffect, useState } from 'react';
import { RiContactsBookLine } from 'react-icons/ri';
export default function Star(props) {
  const status = props.rate - 1;
  const rate = ['별로예요.', '그저 그래요.', '괜찮아요.', '좋아요.', '최고예요.'];

  const [starRate, setStarRate] = useState(status);
  console.log(starRate);
  const [pharase, setPharase] = useState(rate[status]);
  let state = pharase;

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
            checked={starRate === 4 ? 'checked' : ''}
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
            checked={starRate === 3 ? 'checked' : ''}
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
            checked={starRate === 2 ? 'checked' : ''}
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
            checked={starRate === 1 ? 'checked' : ''}
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
            checked={starRate === 0 ? 'checked' : ''}
          />
          <label htmlFor="rate5">★</label>
        </fieldset>
        <div className="star-wrapper-pharases">{state == null ? null : <span>{pharase}</span>}</div>
      </div>
    </form>
  );
}
