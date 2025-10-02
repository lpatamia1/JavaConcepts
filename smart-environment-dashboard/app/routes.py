from flask import Blueprint, render_template, jsonify
from .api import fetch_all_data

main = Blueprint('main', __name__)

@main.route('/')
def index():
    # Fetch real-time data for default city
    data = fetch_all_data("Chicago")
    return render_template('index.html', data=data)

@main.route('/api/data/<city>')
def api_data(city):
    # Return JSON for any city
    data = fetch_all_data(city)
    return jsonify(data)
