package com.kiemtra.thisinh;

public class ThiSinh {
    private String mSBD;
    private String mHoTen;
    private float mToan;
    private float mLy;
    private float mHoa;

    public ThiSinh() {
    }

    public ThiSinh(String mSBD, String mHoTen, float mToan, float mLy, float mHoa) {
        this.mSBD = mSBD;
        this.mHoTen = mHoTen;
        this.mToan = mToan;
        this.mLy = mLy;
        this.mHoa = mHoa;
    }

    public String getmSBD() {
        return mSBD;
    }

    public void setmSBD(String mSBD) {
        this.mSBD = mSBD;
    }

    public String getmHoTen() {
        return mHoTen;
    }

    public void setmHoTen(String mHoTen) {
        this.mHoTen = mHoTen;
    }

    public float getmToan() {
        return mToan;
    }

    public void setmToan(float mToan) {
        this.mToan = mToan;
    }

    public float getmLy() {
        return mLy;
    }

    public void setmLy(float mLy) {
        this.mLy = mLy;
    }

    public float getmHoa() {
        return mHoa;
    }

    public void setmHoa(float mHoa) {
        this.mHoa = mHoa;
    }
}

