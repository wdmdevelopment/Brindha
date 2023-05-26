import React from "react";
import Navbar from "../js/Navbar";
import Hero from "../components/Hero";
import Special from "../components/Special";
import Product from "../components/Product";
import Place from "../components/Place";
import Blog from "../components/Blog";
import Footer from "../js/footer";

function LogoPage() {
  return (
    <div>
      <Navbar />
      {/* <main className="main"> */}
        <Hero />
        <Special />
        <Product />
        <Place />
        <Blog />
        <Footer />
      {/* </main> */}
    </div>
  );
}

export default LogoPage;
