from locust import HttpUser, task, between

class CircleGuardUser(HttpUser):
    wait_time = between(1, 5)

    @task
    def login(self):
        self.client.post("/api/v1/auth/login", json={"username": "test", "password": "password"}, name="Auth Login")

    @task
    def get_questionnaire(self):
        self.client.get("/api/v1/questionnaires/active", name="Get Questionnaire")

    @task
    def submit_survey(self):
        self.client.post("/api/v1/surveys", json={"anonymousId": "hash123", "responses": []}, name="Submit Survey")

    @task
    def validate_gate(self):
        self.client.post("/api/v1/gate/validate", json={"token": "valid-token"}, name="Validate Gate")
