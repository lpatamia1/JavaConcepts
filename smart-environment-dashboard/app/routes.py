from flask import Blueprint, render_template, jsonify
import json
import pandas as pd

main = Blueprint('main', __name__)

# Load mock data
with open('data/mock_data.json') as f:
    env_data = json.load(f)

@main.route('/')
def index():
    # Example: pass data to template
    return render_template('index.html', data=env_data)

@main.route('/api/data')
def api_data():
    return jsonify(env_data)
