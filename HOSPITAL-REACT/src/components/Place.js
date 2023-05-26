import { useState } from "react";
import data from "../components/json/data.json";
import { Fade } from "react-reveal";

const Place = () => {
  const [selectedImage, setSelectedImage] = useState(data.places[0].image);

  return (
    <section className="place section" id="place">
      <div className="place__container container">
        <Fade left delay={400}>
          <h2 className="section__title" style={{ textAlign: "left" }}>
            Make you feels good With <br />
            our comfy fasilities
          </h2>
        </Fade>

        <div className="place__content grid">
          <div className="place__images">
            <img src={selectedImage} alt="" className="place__img-big" />
            <div className="place__img-smalls">
              {data.places.map((place, index) => {
                return (
                  <img
                    key={index}
                    src={place.image}
                    alt=""
                    onClick={() => setSelectedImage(place.image)}
                    className={`place__img-small ${
                      selectedImage === place.image && "active"
                    }`}
                  />
                );
              })}
            </div>
          </div>

          <div className="place__data">
          <Fade right delay={800}>

            <h2 className="place__title">Plan Your Surgery with Medifee</h2>
            <p className="place__description">
              The health care system in India is experiencing
              <br />
              a paradigm shift, a drastic change of face.
              <br />
              This is a welcome development <br />
              considering India's population
            </p>
            </Fade>
          </div>
        </div>
      </div>
    </section>
  );
};
export default Place;
