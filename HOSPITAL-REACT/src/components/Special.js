import hospitals from "../images/hospitals.png";
import cost from "../images/cost.png";
import doctor from "../images/doctor.png";
import { Fade } from "react-reveal";

const Special = () => {
  return (
    <div className="special section container" id="special">
      <div className="special__container">
        <div className="special__box">
          <Fade left delay={500}>
            <h2 className="section__title" style={{ textAlign: "left" }}>
              Medifee - Costs For Hospitals, Doctors, <br />
              Diagnostic Centres & More
            </h2>
          </Fade>
        </div>

        <div className="special__category">
          <Fade bottom delay={800}>
            <div className="special__group">
              <div className="special__img__border">
                <img src={hospitals} alt="" className="special__img" />
              </div>
              <h3 className="special__title" style={{ textAlign: "left" }}>
                Hospitals
              </h3>
              <p className="special__description" style={{ textAlign: "left" }}>
                To help our users, we are building up lists of all the top
                multispeciality as well as super speciality hospitals in India.
              </p>
            </div>
            <div className="special__group">
              <div className="special__img__border">
                <img src={cost} alt="" className="special__img" />
              </div>
              <h3 className="special__title" style={{ textAlign: "left" }}>
                Treatment Cost
              </h3>
              <p className="special__description" style={{ textAlign: "left" }}>
                we will be adding new information about the treatments &
                surgeries in India, with their prices.
              </p>
            </div>
            <div className="special__group">
              <div className="special__img__border">
                <img src={doctor} alt="" className="special__img" />
              </div>
              <h3 className="special__title" style={{ textAlign: "left" }}>
                Doctors
              </h3>
              <p className="special__description" style={{ textAlign: "left" }}>
                Finding the best doctor to suit one's health requirements is
                quite a task, but we at Medifee.com have made this task very
                easy for you.
              </p>
            </div>
          </Fade>
        </div>
      </div>
    </div>
  );
};

export default Special;
