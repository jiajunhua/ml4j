package net.xsoftlab.ml4j.util;

import java.util.ArrayList;
import java.util.List;

import net.xsoftlab.ml4j.supervised.BaseRegression;

import org.jblas.FloatMatrix;

/**
 * 批量梯度下降
 * 
 * @author 王彦超
 *
 */
public class BGD {

	private boolean flag = true;// 是否记录历记录
	private List<FloatMatrix> history = null;// theta history

	private BaseRegression regression;

	public BGD(BaseRegression regression) {
		super();
		this.regression = regression;
		this.history = new ArrayList<FloatMatrix>();
	}

	/**
	 * 初始化
	 * 
	 * @param flag
	 *            是否记录历史记录
	 */
	public BGD(BaseRegression regression, boolean flag) {
		super();
		this.flag = flag;
		this.regression = regression;
		if (flag) {
			this.history = new ArrayList<FloatMatrix>();
		}
	}

	/**
	 * 使用梯度下降计算theta
	 * 
	 * @param x
	 *            特征值
	 * @param y
	 *            标签
	 * @param theta
	 *            参数
	 * @param alpha
	 *            训练速度
	 * @param iterations
	 *            训练次数
	 * @return theta
	 */
	public FloatMatrix compute(FloatMatrix x, FloatMatrix y, FloatMatrix theta, float alpha, int iterations) {

		FloatMatrix h;
		FloatMatrix h1;
		float m = y.length;

		for (int i = 0; i < iterations; i++) {

			h = regression.function(theta).sub(y);
			// x' * h * (alpha / m)
			h1 = x.transpose().mmul(h).mul(alpha / m);
			theta = theta.sub(h1);// theta = theta - h1

			if (flag) {
				history.add(theta);
			}
		}

		return theta;
	}

	public List<FloatMatrix> getHistory() {
		return history;
	}
}
