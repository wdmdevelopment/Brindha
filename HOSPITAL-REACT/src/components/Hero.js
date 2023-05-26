import { Fade } from "react-reveal";

const Hero = () => {
  const bookAppointment = () => {
    window.scrollTo({
      top: 1150,
      behavior: "smooth",
    });
  };

  return (
    <section
      className="hero section"
      id="home"
      style={{ backgroundImage: `url(${require("../images/meditour.png")})` }}
    >
      <Fade top>
        <h1 className="hero__title">
          Treatments & Their Costs <br />
          Top Hospitals & Doctors <br />
          Lab & Diagnostic Centers.
        </h1>
        <button className="hero__button" onClick={bookAppointment}>
          Book Appointment
        </button>
      </Fade>
    </section>
  );
};
export default Hero;
