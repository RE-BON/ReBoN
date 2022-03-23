import '../../../styles/post-star.css';
import React, { useState } from 'react';

export default function Star() {
  const [pharase, setPharase] = useState(null);
  let state = pharase;
  return (
    <form name="myform" id="myform" method="post" action="./save">
      <div className="star-wrapper">
        <fieldset>
          <input onClick={() => setPharase('최고예요.')} type="radio" name="rating" value="5" id="rate1" />
          <label htmlFor="rate1">★</label>
          <input onClick={() => setPharase('좋아요.')} type="radio" name="rating" value="4" id="rate2" />
          <label htmlFor="rate2">★</label>
          <input onClick={() => setPharase('괜찮아요.')} type="radio" name="rating" value="3" id="rate3" />
          <label htmlFor="rate3">★</label>
          <input onClick={() => setPharase('그저 그래요')} type="radio" name="rating" value="2" id="rate4" />
          <label htmlFor="rate4">★</label>
          <input onClick={() => setPharase('별로예요.')} type="radio" name="rating" value="1" id="rate5" />
          <label htmlFor="rate5">★</label>
        </fieldset>
        <div className="star-wrapper-pharases">{state == null ? null : <span>{pharase}</span>}</div>
      </div>
    </form>
  );
}
