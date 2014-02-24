package com.sns.domain;

import java.io.Serializable;

public class Sort implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Order order;

	public Sort(Order order) {
		super();
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}

	public static enum Direction {
		ASC, DESC;
	}

	public static class Order implements Serializable {

		private static final long serialVersionUID = 1522511010900108987L;

		private final Direction direction;
		private final String property;

		public Order(Direction direction, String property) {
			super();
			this.direction = direction;
			this.property = property;
		}

		public Direction getDirection() {
			return direction;
		}

		public String getProperty() {
			return property;
		}

	}

}
